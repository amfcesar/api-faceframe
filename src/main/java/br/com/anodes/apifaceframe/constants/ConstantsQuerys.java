package br.com.anodes.apifaceframe.constants;

public interface ConstantsQuerys {

    String POST_REPOSITORY_FIND_BY_USER_ID = "SELECT post FROM Post post "
            + "WHERE post.user.id = :userId ";

}

