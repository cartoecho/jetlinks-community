package org.jetlinks.community.device.entity;

import lombok.Getter;
import lombok.Setter;
import org.hswebframework.ezorm.rdb.mapping.annotation.*;
import org.hswebframework.web.api.crud.entity.GenericEntity;
import org.hswebframework.web.api.crud.entity.RecordCreationEntity;
import org.hswebframework.web.crud.generator.Generators;
import org.hswebframework.web.validator.CreateGroup;
import org.jetlinks.community.device.enums.DeviceState;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.JDBCType;
import java.util.Map;

@Getter
@Setter
@Table(name = "dev_device_instance")
public class DeviceInstanceEntity extends GenericEntity<String> implements RecordCreationEntity {

    @Override
    @GeneratedValue(generator = Generators.SNOW_FLAKE)
    public String getId() {
        return super.getId();
    }

    @Comment("设备实例名称")
    @Column(name = "name")
    @NotBlank(message = "设备名称不能为空", groups = CreateGroup.class)
    private String name;

    @Comment("说明")
    @Column(name = "describe")
    private String describe;

    @Comment("产品id")
    @Column(name = "product_id",length = 32)
    @NotBlank(message = "产品ID不能为空", groups = CreateGroup.class)
    private String productId;

    @Comment("产品名称")
    @Column(name = "product_name")
    @NotBlank(message = "产品名称不能为空", groups = CreateGroup.class)
    private String productName;

    @Comment("其他配置")
    @Column(name = "configuration")
    @ColumnType(jdbcType = JDBCType.CLOB)
    @JsonCodec
    private Map<String, Object> configuration;

    @Comment("派生元数据,有的设备的属性，功能，事件可能会动态的添加")
    @Column(name = "derive_metadata")
    @ColumnType(jdbcType = JDBCType.CLOB)
    private String deriveMetadata;

    @Column(name = "state")
    @EnumCodec
    @ColumnType(javaType = String.class)
    @DefaultValue("notActive")
    private DeviceState state;

    @Column(name = "creator_id")
    private String creatorId;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "create_time")
    @DefaultValue(generator = Generators.CURRENT_TIME)
    private Long createTime;

    @Comment("注册时间")
    @Column(name = "registry_time")
    private Long registryTime;

    @Column(name = "org_id", length = 32)
    @Comment("所属机构id")
    private String orgId;

}
