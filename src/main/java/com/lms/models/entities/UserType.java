package com.lms.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_type")
public class UserType {
    @Id
    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    public UserType() {}

    public UserType(Long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
