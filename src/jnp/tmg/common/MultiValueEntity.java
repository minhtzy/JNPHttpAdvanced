/**
 *
 * MultiValueEntity
 * Created by @minht on Oct 15, 2018
 */
package jnp.tmg.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import jnp.tmg.modules.assertion.AssertParameter;

public class MultiValueEntity<T extends KeyAndValue> implements Iterable<T> {

    /**
     *
     */
    protected final List<T> entities;

    @Override
    public Iterator<T> iterator() {
        return entities.iterator();
    }

    public MultiValueEntity() {
        entities = new LinkedList<>();
    }

    public MultiValueEntity(List<T> entities) {
        AssertParameter.notNull(entities, "entities");
        this.entities = entities;

    }

    public void addEntity(T entity) {
        this.entities.add(entity);
    }
    public int size() {
        return entities.size();
    }

    public boolean isExist() {
        return !entities.isEmpty();
    }

    public boolean hasEntity(String key) {
        return getEntity(key) != null;
    }

    public List<T> getEntities() {
        return entities;
    }
    public List<T> getUnmodifiableEntities() {
        return Collections.unmodifiableList(entities);
    }
    public T getEntity(String key) {
        AssertParameter.notNull(key, "Key");
        for (T entity : entities) {    
            //System.out.println(entity + "------------------" + entity.getKey());
            if (entity != null && entity.getKey().equalsIgnoreCase(key)) {
                return entity;
            }
        }
        return null;
    }
    public List<T> getEntities(String key) {
        final List<T> entity_list = new ArrayList<>();

        for (T entity : entities) {
            if (entity.getKey().equalsIgnoreCase(key)) {
                entity_list.add(entity);
            }
        }
        return entity_list;
    }

    public String getValue(String key) {
        final T entity = getEntity(key);
        if (null == entity) {
            return null;
        }
        return entity.getValue();
    }

    public List<String> getValues(String key) {
        List<T> list = getEntities(key);
        final List<String> stringList = new LinkedList<>();
        list.forEach((entity) -> {
            stringList.add(entity.getValue());
        });
        return stringList;
    }
        @Override
    public String toString() {
        if(!isExist()) {
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        entities.forEach((entity) -> {
            builder.append(entity).append("\r\n"); // CRLF
        });
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
}
