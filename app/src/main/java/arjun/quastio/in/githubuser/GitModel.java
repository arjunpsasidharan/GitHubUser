
package arjun.quastio.in.githubuser;

public class GitModel
{
    private String incomplete_results;

    private Item[] items;

    private String total_count;

    public String getIncomplete_results ()
    {
        return incomplete_results;
    }

    public void setIncomplete_results (String incomplete_results)
    {
        this.incomplete_results = incomplete_results;
    }

    public Item[] getItems ()
    {
        return items;
    }

    public void setItems (Item[] items)
    {
        this.items = items;
    }

    public String getTotal_count ()
    {
        return total_count;
    }

    public void setTotal_count (String total_count)
    {
        this.total_count = total_count;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [incomplete_results = "+incomplete_results+", items = "+items+", total_count = "+total_count+"]";
    }
}
