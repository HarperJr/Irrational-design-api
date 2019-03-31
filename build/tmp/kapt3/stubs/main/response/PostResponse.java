package response;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0015J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007H\u00c6\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\u0003H\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\t\u0010?\u001a\u00020\rH\u00c6\u0003J\t\u0010@\u001a\u00020\rH\u00c6\u0003J\t\u0010A\u001a\u00020\rH\u00c6\u0003J\u0093\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00072\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010F\u001a\u00020\rH\u00d6\u0001J\t\u0010G\u001a\u00020\u0003H\u00d6\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u000e\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR\u001a\u0010\u000f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R\u001a\u0010\u0014\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\'\"\u0004\b+\u0010)R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\'\"\u0004\b-\u0010)R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001f\"\u0004\b/\u0010!R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\'\"\u0004\b1\u0010)R \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001b\"\u0004\b3\u0010\u001dR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\'\"\u0004\b5\u0010)\u00a8\u0006H"}, d2 = {"Lresponse/PostResponse;", "", "id", "", "artist", "Lresponse/ArtistResponse;", "arts", "", "Lresponse/ArtResponse;", "title", "subtitle", "description", "likes", "", "bookmarks", "comments", "tags", "Lresponse/TagResponse;", "categories", "Lresponse/CategoryResponse;", "date", "(Ljava/lang/String;Lresponse/ArtistResponse;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILjava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getArtist", "()Lresponse/ArtistResponse;", "setArtist", "(Lresponse/ArtistResponse;)V", "getArts", "()Ljava/util/List;", "setArts", "(Ljava/util/List;)V", "getBookmarks", "()I", "setBookmarks", "(I)V", "getCategories", "setCategories", "getComments", "setComments", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "getDescription", "setDescription", "getId", "setId", "getLikes", "setLikes", "getSubtitle", "setSubtitle", "getTags", "setTags", "getTitle", "setTitle", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "id-server"})
public final class PostResponse {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String id;
    @org.jetbrains.annotations.NotNull()
    private response.ArtistResponse artist;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<response.ArtResponse> arts;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String title;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String subtitle;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String description;
    private int likes;
    private int bookmarks;
    private int comments;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<response.TagResponse> tags;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<response.CategoryResponse> categories;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String date;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final response.ArtistResponse getArtist() {
        return null;
    }
    
    public final void setArtist(@org.jetbrains.annotations.NotNull()
    response.ArtistResponse p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<response.ArtResponse> getArts() {
        return null;
    }
    
    public final void setArts(@org.jetbrains.annotations.NotNull()
    java.util.List<response.ArtResponse> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSubtitle() {
        return null;
    }
    
    public final void setSubtitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final void setDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getLikes() {
        return 0;
    }
    
    public final void setLikes(int p0) {
    }
    
    public final int getBookmarks() {
        return 0;
    }
    
    public final void setBookmarks(int p0) {
    }
    
    public final int getComments() {
        return 0;
    }
    
    public final void setComments(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<response.TagResponse> getTags() {
        return null;
    }
    
    public final void setTags(@org.jetbrains.annotations.NotNull()
    java.util.List<response.TagResponse> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<response.CategoryResponse> getCategories() {
        return null;
    }
    
    public final void setCategories(@org.jetbrains.annotations.NotNull()
    java.util.List<response.CategoryResponse> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDate() {
        return null;
    }
    
    public final void setDate(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public PostResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    response.ArtistResponse artist, @org.jetbrains.annotations.NotNull()
    java.util.List<response.ArtResponse> arts, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    java.lang.String description, int likes, int bookmarks, int comments, @org.jetbrains.annotations.NotNull()
    java.util.List<response.TagResponse> tags, @org.jetbrains.annotations.NotNull()
    java.util.List<response.CategoryResponse> categories, @org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final response.ArtistResponse component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<response.ArtResponse> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<response.TagResponse> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<response.CategoryResponse> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final response.PostResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    response.ArtistResponse artist, @org.jetbrains.annotations.NotNull()
    java.util.List<response.ArtResponse> arts, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    java.lang.String description, int likes, int bookmarks, int comments, @org.jetbrains.annotations.NotNull()
    java.util.List<response.TagResponse> tags, @org.jetbrains.annotations.NotNull()
    java.util.List<response.CategoryResponse> categories, @org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}