import autobind from 'autobind-decorator';
import { action, observable, reaction } from 'mobx';

export interface IPost {
    dealId: string,
    postId: number,
    imgPaths: IImg[]
    title: string,
    category: string,
    hashtag: IHashtag[],
    contents: string,
    price: number,
    buyerId: number,
    sellerId: number,
    user: IUser,
}

export interface IUser {
    name: string,
    address: string | null,
    trust: number,
    numOfPosts: number,
    imgurl: string,
}

export interface IHashtag {
    hashtag: string,
}

export interface IImg {
    imgPath: string;
}

export const initialPost = {
};

@autobind
class PostStore {
    @observable post: IPost | undefined;
    @observable postId: number = -1;

    constructor(initialData = initialPost, root: any) {

    }

    @action
    getDeal() {
        return this.post;
    }

    @action
    setDeal(post: IPost) {
        this.post = post;
    }
}

export default PostStore;
