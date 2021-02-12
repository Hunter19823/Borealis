package pie.ilikepiefoo2.borealis.page;

public abstract class Dirty {
    private boolean isDirty = true;
    private boolean isAlwaysDirty = true;
    protected String cachedContent;

    public boolean isDirty()
    {
        return isDirty || isAlwaysDirty;
    }

    public void cache(String cachedContent,boolean dirty)
    {
        this.cachedContent = cachedContent;
        isDirty = dirty;
    }

    public void markDirty(boolean dirty)
    {
        isDirty = dirty;
    }

    public boolean isAlwaysDirty()
    {
        return isAlwaysDirty;
    }

    public <D extends WebPage> D neverDirty()
    {
        isAlwaysDirty = false;
        return (D) this;
    }
    public <D extends WebPage> D alwaysDirty()
    {
        isAlwaysDirty = true;
        return (D) this;
    }
}
