package database.collection.impl;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u00a2\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00028\u0000H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u001f\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u001b\u0010\u0015\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00028\u0000H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001f\u0010\u0017\u001a\u00020\u000f2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00028\u0000H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Ldatabase/collection/impl/DocumentCollection;", "T", "Ldatabase/document/Document;", "Ldatabase/collection/Collection;", "collection", "Lorg/litote/kmongo/coroutine/CoroutineCollection;", "(Lorg/litote/kmongo/coroutine/CoroutineCollection;)V", "all", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "contains", "", "t", "(Ldatabase/document/Document;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ids", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "find", "idRange", "insert", "update", "id-server"})
public class DocumentCollection<T extends database.document.Document> implements database.collection.Collection<T> {
    private final org.litote.kmongo.coroutine.CoroutineCollection<T> collection = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object all(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends T>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object find(@org.jetbrains.annotations.NotNull()
    java.lang.String p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super T> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object find(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends T>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.lang.String p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object update(@org.jetbrains.annotations.NotNull()
    T p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    T p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object contains(@org.jetbrains.annotations.NotNull()
    T p0, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> p1) {
        return null;
    }
    
    public DocumentCollection(@org.jetbrains.annotations.NotNull()
    org.litote.kmongo.coroutine.CoroutineCollection<T> collection) {
        super();
    }
}