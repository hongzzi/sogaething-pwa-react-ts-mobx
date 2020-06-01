import autobind from 'autobind-decorator';
import { action, observable, reaction } from 'mobx';

export interface IPost {
    postId: number,
    title: string,
    category: string,
    imgPaths: string[],
    hashtag: string[],
    contents: string,
    transaction: string,
    price: number,
    user: IUser,
    viewCount: number,
    isBuy: boolean,
    deal: string,
    dealState: string,
}

export interface IUser {
    name: string,
    address: string | null,
    trust: number,
    numOfPosts: number,
    imgurl: string,
}

export const initialPost = {
};

@autobind
class PostStore {
    @observable post: IPost | undefined;
    @observable title: string = '';
    @observable category: string = '';
    @observable imgPaths: string[] = [];
    @observable hashtag: string[] = [];
    @observable contents: string = '';
    @observable transaction: string = '';
    @observable price: number = 0;

    constructor(initialData = initialPost, root: any) {

    }

    @action
    getPost() {
        return this.post;
    }

    @action
    setPost(post: IPost) {
        this.post = post;
    }

    @action
    getTitle() {
        return this.title;
    }

    @action
    setTitle(title: string) {
        this.title = title;
    }

    @action
    getCategory() {
        return this.category;
    }

    @action
    setCategory(category: string) {
        this.category = category;
    }

    @action
    getImgPaths() {
        return this.imgPaths;
    }

    @action
    setImgPaths(imgPaths: string[]) {
        this.imgPaths = imgPaths;
    }

    @action
    getHashtag() {
        return this.hashtag;
    }

    @action
    setHashtag(hashtag: string[]) {
        this.hashtag = hashtag;
    }

    @action
    getContents() {
        return this.contents;
    }

    @action
    setContents(contents: string) {
        this.contents = contents;
    }

    @action
    getTransaction() {
        return this.transaction;
    }

    @action
    setTransaction(transaction: string) {
        this.transaction = transaction;
    }

    @action
    getPrice() {
        return this.price;
    }

    @action
    setPrice(price: number) {
        this.price = price;
    }
}

export default PostStore;
