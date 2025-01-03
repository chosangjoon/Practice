@Entity
public class Category {
    // 엔티티 클래스임을 나타냄   
    // 기본키(Primary Key) 지정
    // 자동 증가 전략 사용  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

    // 엔티티가 다대일 관계를 가지는 경우 사용
    // 엔티티 간의 관계를 설정하는 어노테이션   
    @OneToMany(mappedBy = "category") //category 필드를 통해 연관관계를 맺음            
    private List<Post> posts = new ArrayList<>();//posts 필드는 Post 엔티티와 연관관계를 가지는 필드
} 