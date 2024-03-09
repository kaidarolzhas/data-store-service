package kz.olzhas.kafka.datastoremicroservice.repository;


import kz.olzhas.kafka.datastoremicroservice.config.RedisSchema;
import kz.olzhas.kafka.datastoremicroservice.model.MeasurementType;
import kz.olzhas.kafka.datastoremicroservice.model.Summary;
import kz.olzhas.kafka.datastoremicroservice.model.SummaryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SummaryRepositoryImpl implements SummaryRepository {

    private final JedisPool jedisPool;

    @Override
    public Optional<Summary> findBySensorId(
            long sensorId, Set<MeasurementType> measurementTypes, Set<SummaryType> summaryTypes
    ) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (!jedis.sismember(
                    RedisSchema.sensorKeys(),
                    String.valueOf(sensorId)
            )) {
                return Optional.empty();
            }
            if (measurementTypes.isEmpty() && !summaryTypes.isEmpty()) {
                return getSummary(
                        sensorId,
                        Set.of(MeasurementType.values()),
                        summaryTypes,
                        jedis
                );
            } else if (!measurementTypes.isEmpty() && summaryTypes.isEmpty()) {
                return getSummary(
                        sensorId,
                        measurementTypes,
                        Set.of(SummaryType.values()),
                        jedis
                );
            } else {
                return getSummary(
                        sensorId,
                        measurementTypes,
                        summaryTypes,
                        jedis
                );
            }
        }
    }

    private Optional<Summary> getSummary(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes,
            Jedis jedis
    ) {
        Summary summary = new Summary();
        summary.setSensorId(sensorId);
        for (MeasurementType meType : measurementTypes) {
            for (SummaryType sType : summaryTypes) {
                Summary.SummaryEntry entry = new Summary.SummaryEntry();
                entry.setSummaryType(sType);
                String value = jedis.hget(
                        RedisSchema.summaryKey(sensorId, meType),
                        sType.name().toLowerCase()
                );
                if (value != null) {
                    entry.setValue(Double.parseDouble(value));
                }
                summary.addValue(meType, entry);
            }
        }
        return Optional.of(summary);
    }
}
