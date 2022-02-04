package com.example.proba.data

interface BlogRepository {

    suspend fun downloadBlog(): List<Blog>

    suspend fun getLocalBlogs(): List<Blog>

    suspend fun clearLocalBlogs()

    suspend fun saveLocalBlogs(listB: List<Blog>)

    suspend fun downloadABlogDetails(blogId: String): BlogDetail

    suspend fun getLocalBlogDetail(blogId: String): BlogDetail?

    suspend fun saveLocalBlogDetails(blogDetail: BlogDetail)
}

class BlogRepositoryImpl (
    private val db: MyDatabase,
    private val api: ApiService
) : BlogRepository {

    override suspend fun downloadBlog(): List<Blog> {
        return api.getBlogs()
    }

    override suspend fun getLocalBlogs(): List<Blog> {
        return db.blogsDAO().getBlogs()
    }

    override suspend fun clearLocalBlogs() {
        db.blogsDAO().clearBlogs()
    }

    override suspend fun saveLocalBlogs(listB: List<Blog>) {
        db.blogsDAO().insertBlogs(listB)
    }

    override suspend fun downloadABlogDetails(blogId: String): BlogDetail {
        return api.getBlogDetail(blogId)
    }

    override suspend fun getLocalBlogDetail(blogId: String): BlogDetail? {
        return db.blogDetailDAO().getBlogDetail(blogId)
    }

    override suspend fun saveLocalBlogDetails(blogDetail: BlogDetail) {
        db.blogDetailDAO().insertBlogDetail(blogDetail)
    }
}
