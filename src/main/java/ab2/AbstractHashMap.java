package ab2;

/**
 * Eine abstrakte Klasse zum Implementieren von HashMaps. HashMaps speichern
 * eine Menge an Schlüsseln (hier ein Integer), und zu jedem Schlüssel einen
 * entsprechenden Wert (hier ein String).
 */
public abstract class AbstractHashMap {

    private Integer[] keys;
    private String[] values;

    private void checkInitialized()
    {
        if (keys == null) throw new IllegalStateException();
        if (values == null) throw new IllegalStateException();
    }

    /**
     * Initialisiert die Tabelle mit der gegebenen Größe (Anzahl der Elemente in dem Array).
     */
    protected void initTable(int size)
    {
        if(keys != null) throw new IllegalStateException();
        if(values != null) throw new IllegalStateException();

        values = new String[size];
        keys = new Integer[size];
    }

    /**
     * Setzt einen Schlüssel und Wert an die angegebene Stelle. Ein bereits vorhandener Schlüssel/Wert wird überschrieben.
     */
    protected void setKeyAndValue(int idx, int key, String value)
    {
        checkInitialized();

        values[idx] = value;
        keys[idx] = key;
    }

    /**
     * Liefert den Wert an der angegebenen Stelle
     */
    protected String getValue(int idx)
    {
        checkInitialized();

        return values[idx];
    }

    /**
     * Liefert den Schlüssel an der angegebenen Stelle
     */
    protected Integer getKey(int idx)
    {
        checkInitialized();

        return keys[idx];
    }

    /**
     * Liefert true, wenn die jeweilige Stelle nicht belegt ist. Andernfalls false.
     */
    protected boolean isEmpty(int idx)
    {
        checkInitialized();

        return values[idx] == null;
    }

    /**
     * Setzt die Tabelle zurück. Die Größe bleibt erhalten.
     */
    protected void clear()
    {
        values = null;
        keys = null;
    }

    /**
     * Gibt die Größe der Hashtabelle zurück (d.h. die Größe der
     * zugrundeliegenden Arrays).
     */
    public int capacity()
    {
        checkInitialized();

        return values.length;
    }

    /**
     * Fügt ein Key-Value-Paar in die Hashtabelle ein. Wird ein Schlüssel
     * abermals eingefügt, soll das gespeicherte Value-Objekt ersetzt werden.
     *
     * @param key Schlüssel, darf nicht null sein
     * @param value Der zum Schlüssel gehörige Wert, darf nicht null sein
     *
     * @return liefert false, falls die Tabelle bereits voll ist
     */
    public abstract boolean put(int key, String value);

    /**
     * Liefert das gespeicherte Value-Objekt oder null, falls unter dem
     * Schlüssel nichts gespeichert ist
     *
     * @param key der gesuchte Schlüssel
     *
     * @return null, falls der Schlüssel nicht vorkomment, sonst der Wert
     */
    public abstract String get(int key);

    /**
     * Liefert die Anzahl der gespeicherten Elemente in der Hashtabelle
     *
     * @return Anzahl der gespeicherten Elemente
     */
    public abstract int count();
}
