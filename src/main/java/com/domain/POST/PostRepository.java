//PostRepository 인터페이스는 JpaRepository를 상속받아 기본적인 CRUD 기능을 제공
//Post 엔티티와 Long 타입을 사용하여 데이터베이스와 상호작용
public interface PostRepository extends JpaRepository<Post, Long> {
    //findByTitleContainingOrContentContaining 메서드는 제목이나 내용에 포함된 게시물을 검색하는 메서드
    //Page<Post>는 페이지 정보를 담고 있는 객체
    //Pageable은 페이지 요청 정보를 담고 있는 객체  
    //titleKeyword는 검색할 제목 키워드
    //contentKeyword는 검색할 내용 키워드
    //pageable은 페이지 요청 정보를 담고 있는 객체
    Page<Post> findByTitleContainingOrContentContaining(
        String titleKeyword, 
        String contentKeyword, 
        Pageable pageable
    );
} 