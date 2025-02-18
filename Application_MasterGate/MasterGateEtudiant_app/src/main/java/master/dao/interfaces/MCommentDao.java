package master.dao.interfaces;

import java.util.List;

import master.beans.MComment;
import master.dao.exception.MCommentDaoException;

public interface MCommentDao {
	public List<MComment> getAllComments(int idMaster) throws MCommentDaoException ;
	public void addComment(MComment com) throws MCommentDaoException;
}
