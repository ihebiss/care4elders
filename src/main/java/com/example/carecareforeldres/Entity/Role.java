package com.example.carecareforeldres.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role  implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TypeRole name;
    @ManyToOne
    private User UserAuth;
}
