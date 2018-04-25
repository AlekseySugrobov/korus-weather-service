package ru.sugrobov.weather.model.history;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.sugrobov.weather.model.response.OperationResponse;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryResponse extends OperationResponse {
    List<HistoryRecord> history;
}
