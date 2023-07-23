package com.example.Shopping_portal.Dto.RequestDto;

import com.example.Shopping_portal.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDto {
    String customerMobile;

    String cardNo;

    int cvv;

    Date validTill;

    CardType cardType;
}
