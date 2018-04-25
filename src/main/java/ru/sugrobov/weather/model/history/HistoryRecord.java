package ru.sugrobov.weather.model.history;

import lombok.Data;
import ru.sugrobov.weather.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class HistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private UserAction userAction;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date actionDate;

    public HistoryRecord(User user, UserAction userAction, String description){
        this.user = user;
        this.userAction = userAction;
        this.description = description;
        this.actionDate = new Date();
    }

    public HistoryRecord(){}
}
