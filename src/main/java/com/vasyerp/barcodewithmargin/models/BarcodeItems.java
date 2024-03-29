package com.vasyerp.barcodewithmargin.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.*;

@Entity
@Table(name = "barcode_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarcodeItems {
    @Id
    private Long flag;

    @Column(name = "varient_name")
    private String varient_name;

    @Column(name = "mrp")
    private BigDecimal mrp;

    @Column(name = "display_branch_name")
    private String display_branch_name;

    @Column(name = "name")
    private String name;

    @Column(name = "item_code")
    private String item_code;

}
