package com.br.lp3.model.rmi;

import com.br.lp3.model.entities.Userlp3;
import java.io.IOException;
import javax.ejb.Local;

/**
 *
 * @author Leandro Meneguzzi 3144893-3
 * @author Lucas Gianfrancesco 3147173-0
 * @author Pedro Morelatto 3142463-5
 */
@Local
public interface LogWriterLocal {

    public void logWriter() throws IOException, Throwable;

    public void logWriter(Userlp3 user) throws IOException;

    public void logWriter(Double value, Userlp3 receiver, boolean funfo) throws IOException;

    public void logWriter(Double value, boolean funfo) throws IOException;

}
