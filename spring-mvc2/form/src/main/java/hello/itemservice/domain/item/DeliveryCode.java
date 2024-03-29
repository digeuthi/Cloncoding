package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST : 빠른 배송
 * NOMAL : 일반 배송
 * SLOW : 느린 배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode { //배송과 관련된것
    private String code;
    private String displayName;
}
