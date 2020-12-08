package com.saas.datamiddleend.entity.domain.database;

import lombok.*;

import java.io.Serializable;

/**
 * R_DATABASE
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RDatabase implements Serializable {
    private Long idDatabase;

    private String name;

    private Integer idDatabaseType;

    private Integer idDatabaseContype;

    private String hostName;

    private String databaseName;

    private Integer port;

    private String username;

    private String password;

    private String servername;

    private String dataTbs;

    private String indexTbs;

    private static final long serialVersionUID = 1L;
}