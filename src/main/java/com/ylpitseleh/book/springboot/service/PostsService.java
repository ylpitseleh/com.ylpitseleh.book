package com.ylpitseleh.book.springboot.service;

import com.ylpitseleh.book.springboot.domain.posts.Posts;
import com.ylpitseleh.book.springboot.domain.posts.PostsRepository;
import com.ylpitseleh.book.springboot.web.dto.PostsListResponseDto;
import com.ylpitseleh.book.springboot.web.dto.PostsResponseDto;
import com.ylpitseleh.book.springboot.web.dto.PostsSaveRequestDto;
import com.ylpitseleh.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
    update 기능에서 데이터베이스에 쿼리를 날리는 부분이 없다. = 이게 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
    영속성 컨텍스트란, 엔티티를 영구 저장하는 환경이다.
    일종의 논리적 개념이라고 보면 되며, JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.

    JPA의 엔티티 매니저가 활성화된 상태로(Spring Data JPA를 쓴다면 기본 옵션) 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면
    이 데이터는 영속성 컨텍스트가 유지된 상태이다. 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
    즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다는 것이다 .이 개념을 더티 체킹(dirty checking)이라고 한다.
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }


    //readOnly = true를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선되기 때문에 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천한다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        //:: 람다식 / .map(posts -> new PostsListResponseDto(posts))와 같음
        //postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환하는 메소드
    }


}