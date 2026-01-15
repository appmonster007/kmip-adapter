package org.purpleBean.kmip.benchmark.subjects.model.core.enumeration;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.DataEnumeration;

public class DataEnumerationBenchmarkSubject extends KmipBenchmarkSubject<DataEnumeration> {

    public DataEnumerationBenchmarkSubject() throws Exception {
        DataEnumeration dataEnumeration = DataEnumeration.Standard.DECRYPT.inst();
        initialize(dataEnumeration, DataEnumeration.class);
    }

    @Override
    public String name() {
        return "DataEnumeration";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
