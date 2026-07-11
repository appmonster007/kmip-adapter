package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.Attributes;
import org.purpleBean.kmip.model.core.structure.DefaultsInformation;
import org.purpleBean.kmip.model.core.structure.ObjectDefaults;

import java.util.Collections;
import java.util.List;

public class DefaultsInformationBenchmarkSubject extends KmipBenchmarkSubject<DefaultsInformation> {

    @Getter
    private KmipSpec spec = KmipSpec.V2_1;

    public DefaultsInformationBenchmarkSubject() throws Exception {
        DefaultsInformation subject = DefaultsInformation.of(List.of(
                ObjectDefaults.of(
                        ObjectType.Standard.SYMMETRIC_KEY.inst(),
                        Attributes.of(Collections.emptyList())
                )
        ));
        initialize(subject, DefaultsInformation.class);
    }

    @Override
    public String name() {
        return "DefaultsInformation";
    }
}
