@Entity
public class Tag {
    // 기본키(Primary Key) 지정
    // 자동 증가 전략 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String name;
    
    // 엔티티가 다대다 관계를 가지는 경우 사용
    // 엔티티 간의 관계를 설정하는 어노테이션       
    @ManyToMany(mappedBy = "tags") //tags 필드를 통해 연관관계를 맺음
    private Set<Post> posts = new HashSet<>();//posts 필드는 Post 엔티티와 연관관계를 가지는 필드
} 