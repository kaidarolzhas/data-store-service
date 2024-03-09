package kz.olzhas.kafka.datastoremicroservice.service;

import kz.olzhas.kafka.datastoremicroservice.model.MeasurementType;
import kz.olzhas.kafka.datastoremicroservice.model.Summary;
import kz.olzhas.kafka.datastoremicroservice.model.SummaryType;

import java.util.Set;

public interface SummaryService {
    Summary get(
            long sensorId,
            Set<MeasurementType> measurementTypes,
            Set<SummaryType> summaryTypes
    );
}
