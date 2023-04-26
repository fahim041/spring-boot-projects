package com.dev.restapiblog.service.impl;

import com.dev.restapiblog.entity.Comment;
import com.dev.restapiblog.entity.Post;
import com.dev.restapiblog.exception.PostApiException;
import com.dev.restapiblog.exception.ResourceNotFoundException;
import com.dev.restapiblog.payload.CommentDto;
import com.dev.restapiblog.repository.CommentRepository;
import com.dev.restapiblog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements com.dev.restapiblog.service.CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = getPost(postId);

        Comment comment = getComment(commentId);

        verifyPostComment(post, comment);

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto) {
        Post post = getPost(postId);

        Comment comment = getComment(commentId);

        verifyPostComment(post, comment);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = getPost(postId);

        Comment comment = getComment(commentId);

        verifyPostComment(post, comment);

        commentRepository.delete(comment);
    }

    private Post getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        return post;
    }

    private Comment getComment(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));

        return comment;
    }

    private void verifyPostComment(Post post, Comment comment){
        if(!comment.getPost().getId().equals(post.getId())){
            throw new PostApiException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to post");
        }
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
        return comment;
    }
}
