package database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Ldatabase/MongoDatabase;", "Ldatabase/Database;", "databaseName", "", "(Ljava/lang/String;)V", "database", "Lorg/litote/kmongo/coroutine/CoroutineDatabase;", "artists", "Ldatabase/collection/ArtistCollection;", "arts", "Ldatabase/collection/ArtCollection;", "avatars", "Ldatabase/collection/AvatarCollection;", "bookmarks", "Ldatabase/collection/BookmarkCollection;", "categories", "Ldatabase/collection/CategoryCollection;", "categoriesInPosts", "Ldatabase/collection/CategoryInPostCollection;", "comments", "Ldatabase/collection/CommentCollection;", "followers", "Ldatabase/collection/FollowerCollection;", "posts", "Ldatabase/collection/PostCollection;", "tags", "Ldatabase/collection/TagCollection;", "tagsInPosts", "Ldatabase/collection/TagInPostCollection;", "id-server"})
public final class MongoDatabase implements database.Database {
    private final org.litote.kmongo.coroutine.CoroutineDatabase database = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.ArtCollection arts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.ArtistCollection artists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.AvatarCollection avatars() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.BookmarkCollection bookmarks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.CategoryCollection categories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.TagCollection tags() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.CommentCollection comments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.FollowerCollection followers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.PostCollection posts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.TagInPostCollection tagsInPosts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public database.collection.CategoryInPostCollection categoriesInPosts() {
        return null;
    }
    
    public MongoDatabase(@org.jetbrains.annotations.NotNull()
    java.lang.String databaseName) {
        super();
    }
}