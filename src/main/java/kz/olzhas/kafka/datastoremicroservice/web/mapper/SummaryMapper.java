package kz.olzhas.kafka.datastoremicroservice.web.mapper;

import kz.olzhas.kafka.datastoremicroservice.model.Summary;
import kz.olzhas.kafka.datastoremicroservice.model.SummaryDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SummaryMapper extends  Mappable<Summary, SummaryDto> {
}
