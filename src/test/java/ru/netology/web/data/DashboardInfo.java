package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardInfo {
    private String numberFirstCard;
    private String balanceFirstCard;
    private String numberSecondCard;
    private String balanceSecondCard;
}
