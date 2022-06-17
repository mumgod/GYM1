package com.gym.GYM.company.service;

import com.gym.GYM.company.dao.CommentDAO;
import com.gym.GYM.company.dto.CommentDTO;
import com.gym.GYM.company.dto.CompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.stream.events.Comment;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    List<CommentDTO> commentList = new ArrayList<CommentDTO>();

    @Autowired
    private CommentDAO commentdao;

    private CompanyDTO company = new CompanyDTO();

    @Override
    public List<CommentDTO> commentRegist(CommentDTO comment) {
        int result = commentdao.commentRegist(comment);

        if (result > 0) {
            commentList = commentdao.commentList(comment.getReviewCompanyCode());
        } else {
            commentList = null;
        }

        companyReviewRate(company.getCompanyCode());

        return commentList;
    }

    @Override
    public List<CommentDTO> commentList(String reviewCompanyCode) {
        commentList = commentdao.commentList(reviewCompanyCode);
        return commentList;
    }

    @Override
    public List<CommentDTO> commentModify(CommentDTO comment) {
        int result = commentdao.commentModify(comment);

        companyReviewRate(company.getCompanyCode());

        if (result > 0) {
            commentList = commentdao.commentList(comment.getReviewCompanyCode());
        } else {
            commentList = null;
        }
        return commentList;
    }

    @Override
    public List<CommentDTO> commentDelete(CommentDTO comment) {
        int result = commentdao.commentDelete(comment);

        companyReviewRate(company.getCompanyCode());

        if (result > 0) {
            commentList = commentdao.commentList(comment.getReviewCompanyCode());
        } else {
            commentList = null;
        }
        return commentList;
    }

    public void companyReviewRate(String companyCode){
        Double companyReviewRate = commentdao.companyReviewRate(companyCode);
        if(companyReviewRate == null) {
            companyReviewRate = 0.0;
        }

        CommentDTO comment = new CommentDTO();
        company.setCompanyCode(companyCode);
        comment.setReviewRate(companyReviewRate);

        commentdao.updateRate(comment);
    }
}
