package org.purpleBean.kmip;

import org.purpleBean.kmip.common.structure.ProtocolVersion;

/**
 * Represents the header of a KMIP Request Message.
 * <p>
 * This interface defines the essential components of a KMIP request header, which
 * contains metadata about the request itself. The primary piece of information in the
 * request header is the protocol version being used for the communication.
 *
 * <p><b>Key Components:</b></p>
 * <ul>
 *   <li><b>Protocol Version:</b> Specifies the version of the KMIP protocol that the
 *       client is using to format the request. This allows the server to correctly
 *       interpret the message and respond appropriately.</li>
 * </ul>
 *
 * <p>Implementations of this interface will provide a concrete representation of the
 * KMIP request header, facilitating the serialization and deserialization of this
 * critical part of a KMIP request.</p>
 *
 * @see KmipStructure
 * @see RequestMessageStructure
 * @see ProtocolVersion
 */
public interface RequestHeaderStructure extends KmipStructure {

    /**
     * Retrieves the {@link ProtocolVersion} from the request header.
     * <p>
     * The protocol version indicates which version of the KMIP specification the
     * request conforms to.
     *
     * @return The {@link ProtocolVersion} of the request.
     */
    ProtocolVersion getProtocolVersion();
}
