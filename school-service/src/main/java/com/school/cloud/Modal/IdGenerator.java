package com.school.cloud.Modal;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class IdGenerator extends SequenceStyleGenerator {
    public static final String prefix = "SCH";
    public static final String prefixDefault = "";
    private String SCH;

    public static final String numberParam = "numberFormat";
    public static final String numberparamDefault = "%d";

    private String numberFormat;

    @Override
    public Serializable generate(SharedSessionContractImplementor session,Object object) throws HibernateException{
        return SCH + String.format(numberFormat,super.generate(session,object));
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        SCH = ConfigurationHelper.getString(prefix,
                params, prefixDefault);
        numberFormat = ConfigurationHelper.getString(numberParam,
                params, numberparamDefault);
    }
}
