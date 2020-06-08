import autobind from 'autobind-decorator';
import { action, observable, reaction, toJS } from 'mobx';

export interface IPost {
    title: string,
    category: string,
    imgPaths: string[],
    hashtag: string[],
    contents: string,
    transaction: string,
    price: number,
}

export interface IUser {
    userId: number,
    name: string,
    address: string | null,
    trust: number,
    numOfPosts: number,
    imgurl: string,
}

export interface IPostResponseDto {
    createPost: {
        state: string,
        postId: number,
    },
}

export const initialPost = {
    hashtag: [],
};

@autobind
class PostStore {
    post: IPost | undefined;
    hashSet: Set<string> = new Set();
    imgSet: Set<string> = new Set();
    @observable title: string = '';
    @observable category: string = '';
    @observable imgPaths: string[] = [];
    @observable hashtag: string[] = [];
    @observable contents: string = '';
    @observable transaction: string = '';
    @observable price: number = 0;
    @observable tag: string = '';

    constructor(root: any, initialData?: PostStore) {
        this.post = {
            title: '',
            category: '',
            imgPaths: [],
            hashtag: [],
            contents: '',
            transaction: '',
            price: 0,
        };
        this.tag = '';
    }

    @action
    getPost() {
        this.post = {
            title: this.title,
            category: this.category,
            imgPaths: toJS(this.imgPaths),
            hashtag: toJS(this.hashtag),
            contents: this.contents,
            transaction: this.transaction,
            price: this.price,
        }
        return this.post;
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
        return toJS(this.imgPaths);
    }

    @action
    setImgPaths(imgPaths: string) {
        this.imgPaths = this.imgPaths.concat(imgPaths);
    }

    @action
    getHashtag() {
        return toJS(this.hashtag);
    }

    @action
    setHashtag(hashtag: string) {
        this.hashSet.add(hashtag);
        this.hashtag = Array.from(this.hashSet.values());
    }

    @action
    removeHashtag(hashtag: string) {
        this.hashSet.delete(hashtag);
        this.hashtag = Array.from(this.hashSet.values());
    }

    @action
    getTag() {
        return this.tag;
    }

    @action
    setTag(tag: string) {
        this.tag = tag;
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
