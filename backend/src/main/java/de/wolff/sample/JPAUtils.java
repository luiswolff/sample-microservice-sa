package de.wolff.sample;

import javax.persistence.EntityTransaction;

/**
 * Created by luis- on 07.06.2017.
 */
public class JPAUtils {

    public static <T> T transactional(EntityTransaction transaction, ExceptionSupplier<T> supplier) {
        try {
            transaction.begin();
            T t =  supplier.get();
            transaction.commit();
            return t;
        } catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    static void transactional(EntityTransaction transaction, ExceptionAction action) {
        try {
            transaction.begin();
            action.act();
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    interface ExceptionSupplier <T> {

        T get() throws Exception;

    }

    interface ExceptionAction {

        void act() throws Exception;

    }

}
