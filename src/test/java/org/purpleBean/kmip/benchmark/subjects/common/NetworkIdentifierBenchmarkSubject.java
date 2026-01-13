package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierBenchmarkSubject extends KmipBenchmarkSubject<NetworkIdentifier> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public NetworkIdentifierBenchmarkSubject() throws Exception {
        NetworkIdentifier networkIdentifier = NetworkIdentifier.builder().value("test-network-id").build();
        initialize(networkIdentifier, NetworkIdentifier.class);
    }

    @Override
    public String name() {
        return "NetworkIdentifier";
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