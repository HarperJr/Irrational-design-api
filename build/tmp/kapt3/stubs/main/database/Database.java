package database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H&J\b\u0010\u0016\u001a\u00020\u0017H&\u00a8\u0006\u0018"}, d2 = {"Ldatabase/Database;", "", "artists", "Ldatabase/collection/ArtistCollection;", "arts", "Ldatabase/collection/ArtCollection;", "avatars", "Ldatabase/collection/AvatarCollection;", "bookmarks", "Ldatabase/collection/BookmarkCollection;", "categories", "Ldatabase/collection/CategoryCollection;", "categoriesInPosts", "Ldatabase/collection/CategoryInPostCollection;", "comments", "Ldatabase/collection/CommentCollection;", "followers", "Ldatabase/collection/FollowerCollection;", "posts", "Ldatabase/collection/PostCollection;", "tags", "Ldatabase/collection/TagCollection;", "tagsInPosts", "Ldatabase/collection/TagInPostCollection;", "id-server"})
public abstract interface Database {
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.ArtCollection arts();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.ArtistCollection artists();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.AvatarCollection avatars();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.BookmarkCollection bookmarks();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.CategoryCollection categories();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.TagCollection tags();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.CommentCollection comments();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.FollowerCollection followers();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.PostCollection posts();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.TagInPostCollection tagsInPosts();
    
    @org.jetbrains.annotations.NotNull()
    public abstract database.collection.CategoryInPostCollection categoriesInPosts();
}