package com.taqui.api_mvc.model;

import com.taqui.api_mvc.dto.UsuarioRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "TAQUI_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL", columnNames = {"ds_email"})
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(
            generator = "geradorIds",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "geradorIds",
            sequenceName = "sq_tb_usuario",
            allocationSize = 1)
    @Column(name = "pk_id_usuario",  columnDefinition = "NUMBER(10)")
    private Long id;

    @Column(name = "nm_exibicao", columnDefinition = "VARCHAR(50)", nullable = false)
    private String nome;

    @Column(name = "ds_email", columnDefinition = "VARCHAR(100)", nullable = false)
    private String email;

    @Column(name = "ds_senha",  columnDefinition = "VARCHAR(15)", nullable = false)
    private String senha;


}
