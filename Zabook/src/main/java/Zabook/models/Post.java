package Zabook.models;


import  Zabook.Until.TimeUntil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor 
@Builder 
@Document(collection = "Posts")
public class Post {
	@Id
	private ObjectId id;

	@DBRef(lazy = false)
	private User user;

	private String contentShare;
	
	private String content;
	
	private LocalDateTime createdAt;
	
	@DBRef(lazy = false)
	private List<Image> image;
	
	@DBRef(lazy = false)
	private List<Video> video;
	
	@DBRef(lazy = false)
	private List<Comment> comment;
	public int getCommentCount() { return comment != null ? comment.size() : 0; }
	
	
	private ObjectId originalPostId; // Nếu là bài chia sẻ, lưu ID của bài gốc
    private boolean isShared;      // Đánh dấu bài viết là bài chia sẻ hay không
    private int shareCount;
	
	// Danh sách người dùng đã like bài viết
    @DBRef(lazy = false)
    private List<User> likedUsers;
    
    // Thêm người dùng vào danh sách likedUsers
    public void addLikedUser(User user) {
        if (likedUsers == null) {
            likedUsers = new ArrayList<>();
        }
        likedUsers.add(user);
    }

    // Xóa người dùng khỏi danh sách likedUsers
    public void removeLikedUser(User user) {
        if (likedUsers != null) {
            likedUsers.remove(user);
        }
    }
    
	
	public Post() {
		this.createdAt = LocalDateTime.now();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

	
	
	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public Post(ObjectId id, User user, String content, LocalDateTime createdAt, List<Image> image, List<Video> video) {
		super();
		this.id = id;
		this.user = user;
		this.content = content;
		this.createdAt = createdAt;
		this.image = image;
		this.video = video;
	}

	//lâm
	public String getTimeAgo() {
       return TimeUntil.timeAgo(createdAt);
    }
	//lâm

	public String getContentShare() {
		return contentShare;
	}

	public void setContentShare(String contentShare) {
		this.contentShare = contentShare;
	}

	public ObjectId getOriginalPostId() {
		return originalPostId;
	}

	public void setOriginalPostId(ObjectId originalPostId) {
		this.originalPostId = originalPostId;
	}

	public boolean isShared() {
		return isShared;
	}

	public void setShared(boolean isShared) {
		this.isShared = isShared;
	}

	public int getShareCount() {
		return shareCount;
	}

	public void setShareCount(int shareCount) {
		this.shareCount = shareCount;
	}

	public List<User> getLikedUsers() {
		return likedUsers;
	}

	public void setLikedUsers(List<User> likedUsers) {
		this.likedUsers = likedUsers;
	}
	
	
	
}
