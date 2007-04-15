package net.lojjic.xul.rdf.impl;

import org.openrdf.repository.RepositoryConnection;

/**
 * Callback interface for executing code within a Sesame repository connection.
 */
interface SesameConnectionCallback<T> {

	T doInConnection(RepositoryConnection conn) throws Exception;

}
