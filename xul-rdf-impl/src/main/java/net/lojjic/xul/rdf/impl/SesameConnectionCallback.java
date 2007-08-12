package net.lojjic.xul.rdf.impl;

import org.openrdf.repository.RepositoryConnection;

/**
 * Callback interface for executing code within a Sesame repository connection.
 * Implement this interface and pass the result as an argument to
 * {@link net.lojjic.xul.rdf.impl.SesameUtils#execute(org.openrdf.repository.Repository, SesameConnectionCallback)}
 * to execute Sesame repository operations without having to worry about connection
 * setup and cleanup.
 */
public interface SesameConnectionCallback<T> {

	T doInConnection(RepositoryConnection conn) throws Exception;

}
