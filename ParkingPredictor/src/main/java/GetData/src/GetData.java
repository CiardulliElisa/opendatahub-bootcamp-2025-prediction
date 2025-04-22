public abstract class GetData {

    // Returns all data from an API
    // @param url - API url
    public abstract Object[] getData(String url);

    // Returns an access token that is used to retrieve data for 48 hours
    public Object[] generateAccessToken() {
        return null;
    }

}
