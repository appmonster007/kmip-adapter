package org.purpleBean.kmip.api.request;

import org.purpleBean.kmip.api.KmipStructure;

/**
 * Represents a single batch item within a KMIP Request Message.
 * <p>
 * This interface serves as a marker for all structures that can be included as a
 * batch item in a KMIP request. A batch item encapsulates a single KMIP operation
 * (e.g., Create, Get, Destroy) and its associated payload.
 *
 * <p>A {@link RequestMessageStructure} contains a list of these batch items, allowing
 * multiple operations to be sent in a single request to the KMIP server.</p>
 *
 * <p>Implementations of this interface will define the specific structure and content
 * required for a particular KMIP operation.</p>
 *
 * @see KmipStructure
 * @see RequestMessageStructure
 */
public interface RequestBatchItemStructure extends KmipStructure {

}
