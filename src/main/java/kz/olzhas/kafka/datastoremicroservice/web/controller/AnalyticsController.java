package kz.olzhas.kafka.datastoremicroservice.web.controller;

import kz.olzhas.kafka.datastoremicroservice.model.MeasurementType;
import kz.olzhas.kafka.datastoremicroservice.model.Summary;
import kz.olzhas.kafka.datastoremicroservice.model.SummaryDto;
import kz.olzhas.kafka.datastoremicroservice.model.SummaryType;
import kz.olzhas.kafka.datastoremicroservice.service.SummaryService;
import kz.olzhas.kafka.datastoremicroservice.web.mapper.SummaryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final SummaryService summaryService;
    private final SummaryMapper summaryMapper;

    @GetMapping("summary/{sensorId}")
    public SummaryDto getSummary(
            @PathVariable long sensorId,
            @RequestParam(value = "mt", required = false)
            Set<MeasurementType> measurementTypes,
            @RequestParam(value = "st", required = false)
            Set<SummaryType> summaryTypes
    ) {
        Summary summary = summaryService.get(
                sensorId,
                measurementTypes,
                summaryTypes
        );

        return summaryMapper.toDto(summary);
    }
}
