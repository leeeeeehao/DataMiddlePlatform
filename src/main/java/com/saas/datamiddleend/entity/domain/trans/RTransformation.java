package com.saas.datamiddleend.entity.domain.trans;

import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
 * R_TRANSFORMATION
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RTransformation implements Serializable {
    private Long idTransformation;

    private Integer idDirectory;

    private String name;

    private String description;

    private String extendedDescription;

    private String transVersion;

    private Integer transStatus;

    private Integer idStepRead;

    private Integer idStepWrite;

    private Integer idStepInput;

    private Integer idStepOutput;

    private Integer idStepUpdate;

    private Integer idDatabaseLog;

    private String tableNameLog;

    private Boolean useBatchid;

    private Boolean useLogfield;

    private Integer idDatabaseMaxdate;

    private String tableNameMaxdate;

    private String fieldNameMaxdate;

    private Double offsetMaxdate;

    private Double diffMaxdate;

    private String createdUser;

    private Date createdDate;

    private String modifiedUser;

    private Date modifiedDate;

    private Integer sizeRowset;

    private static final long serialVersionUID = 1L;
}