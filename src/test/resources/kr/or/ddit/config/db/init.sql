-- sequence
DROP sequence bd_no_sequence;
CREATE sequence bd_no_sequence;

DROP sequence pt_no_sequence;
CREATE sequence pt_no_sequence;

DROP sequence re_no_sequence;
CREATE sequence re_no_sequence;

DROP sequence file_no_sequence;
CREATE sequence file_no_sequence;


-- 파일 삭제
delete from filetable where file_no=1;
delete from filetable where file_no=2;

-- 댓글 삭제
delete from recomment where re_no=1;
delete from recomment where re_no=2;

-- 게시글 삭제
DELETE FROM post WHERE pt_no=3;

-- 게시글 삭제
DELETE FROM post WHERE pt_no=2;

-- 게시글 삭제
DELETE FROM post WHERE pt_no=1;


-- 게시판 삭제
DELETE FROM board WHERE bd_no=1;

-- 게시판 삭제
DELETE FROM board WHERE bd_no=2;


-- 학생 삭제
DELETE FROM student WHERE id=1;

-- 학생추가
INSERT INTO student
	VALUES(1,'song','3ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','한송희',0,'대전시 유성구 장대동','광신빌라 3동 407호',
30,'moon.png','D:\A_TeachingMaterial\7.JspSrpgin\fileUpload','59c7b9a2-b87f-4bc6-ba16-5a781f1180ce',
sysdate
);

-- 게시판 추가
INSERT INTO board
	 VALUES(bd_no_sequence.nextval, 1, '자유게시판', 1, sysdate);

-- 게시물 추가
INSERT INTO post
	 VALUES(pt_no_sequence.nextval, 1, 1, null, pt_no_sequence.nextval, '첫번째 게시글', '게시글', sysdate, 0);

-- 댓글 추가
INSERT INTO recomment
  		VALUES (re_no_sequence.nextval
  					, 1
 					, 1
 					, '댓글 테스트입니다'
 					, 0
					, sysdate);

-- 파일 추가
INSERT INTO filetable
	  		VALUES(file_no_sequence.nextval
	  					, 1
	  					, 'c6966d30-6010-4429-8882-e67efb74dfe4.jpg'
	  					, 'D:\A_TeachingMaterial\7.JspSpring\fileUpload'
	  					, '2607213D5870CF910F.jpg'
	  					, sysdate);
COMMIT;
