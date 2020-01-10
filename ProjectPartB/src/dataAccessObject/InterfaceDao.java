package dataAccessObject;

import java.util.List;

public interface InterfaceDao <E> {
    
    boolean create(E e);
    List<E> findAll();
    E findById(int id);
//    boolean update(E e);
//    boolean delete(int id);
}
