package model;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vendas {
    private Long operacao;
    private Long cliente;
    private Integer quantidade;
    private BigDecimal valorTotal;
}
