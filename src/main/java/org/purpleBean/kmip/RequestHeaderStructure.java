package org.purpleBean.kmip;

import org.purpleBean.kmip.common.structure.ProtocolVersion;

public interface RequestHeaderStructure extends KmipStructure {
    ProtocolVersion getProtocolVersion();
}
