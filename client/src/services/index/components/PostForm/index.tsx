import * as React from 'react';
import { useEffect, useState } from 'react';
import styled from '~/styled';

import { useRouter } from 'next/router';
import CommonBtn from '../../components/CommonBtn';

import { useCreatePostMutation } from '~/generated/graphql';
import CameraFillIcon from '../../assets/img/form-camera.png';
import DropdownIcon from '../../assets/img/form-dropdown.png';
import ExpandIcon from '../../assets/img/form-expand.png';
import useStores from '../../helpers/useStores';
import { IPostResponseDto } from '../../store/PostStore';
import Loader from '../Loader';

export interface IPostFormProps {
}

const category = ['디지털/가전', '가구/인테리어', '유아동/유아도서', '생활/가공식품', '스포츠/레저', '여성잡화', '여성의류', '남성패션/잡화', '게임/취미', '뷰티/미용', '반려동물용품', '도서/티켓/음반', '기타 중고물품'];

export default (props: IPostFormProps) => {
    const router = useRouter();
    const store = useStores();
    const postStore = store.postStore;
    const mutation = useCreatePostMutation();
    const [post, setPost] = useState(postStore.getPost());
    const [previews, setPreviews] = useState(Array());
    const [loading, setLoading] = useState(false);

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            const files = Array.from(e.target.files);
            files.forEach((file) => {
                const reader = new FileReader();
                reader.onloadend = () => {
                    previews.push(reader.result);
                    if (reader.result) {
                        postStore.setImgPaths(reader.result.toString());
                    }
                    setPreviews([...previews]);
                }
                reader.readAsDataURL(file);
            });
        };
    };

    const handleChangeTitle = (event: any) => {
        postStore.setTitle(event.target.value);
        setPost(store.postStore.getPost());
    }
    const handleChangeCategory = (event: any) => {
        postStore.setCategory(event.target.value);
        setPost(store.postStore.getPost());
    }
    const handleChangePrice = (event: any) => {
        const targetValue = event.target.value + '';
        let parseValue = event.target.value;
        if (targetValue.startsWith('0')) {
            parseValue = Number.parseInt(targetValue);
        }
        store.postStore.setPrice(Number.parseInt(parseValue));
        console.log(typeof store.postStore.getPost().price);
        setPost(store.postStore.getPost());
    }
    const handleChangeTransaction = (event: any) => {
        postStore.setTransaction(event.target.value);
        setPost(store.postStore.getPost());
    }
    const handleChangeContents = (event: any) => {
        postStore.setContents(event.target.value);
        setPost(store.postStore.getPost());
    }
    const handleClickHashtag = () => {
        router.push('/form/post/hashtag')
    }
    const handleSubmit = (event: any) => {
        event.preventDefault();
        previews.map((pre) => {postStore.setImgPaths(pre.slice(23))})
        setPost(postStore.getPost());
        if (postStore.getImgPaths().length > 0) {
            setLoading(true);
            mutation({
                variables: {
                    input: {
                        title: post.title,
                        category: post.category,
                        imgPaths: post.imgPaths,
                        hashtag: post.hashtag,
                        contents: post.contents,
                        transaction: post.transaction,
                        price: post.price,
                    },
                },
            }).then((res: { data: IPostResponseDto }) => {
                if (res.data.createPost.state === 'SUCCESS') {
                    setLoading(false);
                    router.push(`/post/${res.data.createPost.postId}?isPosted=true`);
                }else {
                    setLoading(false);
                    router.push('/main');
                }
            }).catch((error) => {
                console.error(error);
            })
            .finally(() => {
                setLoading(false);
            })
        } else {
            alert('사진을 등록해주세요!');
        }
    }

    if (loading) {
        return <Loader />
    }

    return (
        <Wrapper>
            <FormContainer>
                <ImageSelector>
                    <ImageBtn htmlFor='img-file'>
                        <CameraIcon src={CameraFillIcon} />
                        <ImageSpan>{!previews && '0/10'} {previews && previews.length + '/10'}</ImageSpan>
                    </ImageBtn>
                    <FileInput id={'img-file'} type={'file'} multiple accept={'image/png, image/jpeg, image/jpg'} onChange={handleFileChange} />
                    <PreviewContainer>
                        {
                            postStore.imgPaths.map((preview, index) => (<PreviewImg src={preview} key={index} />))
                        }
                    </PreviewContainer>
                </ImageSelector>
                <InputContainer>
                    <Input type={'text'} value={post.title} placeholder={'제목'} onChange={handleChangeTitle} required />
                </InputContainer>
                <InputContainer>
                    <Select value={post.category} onChange={handleChangeCategory}> <Option value={''}> 카테고리 </Option>
                        {
                            category.map((category, index) => (
                                <Option key={index} value={category}>{category}</Option>
                            ))
                        }
                    </Select>
                </InputContainer>
                <InputContainer>
                    <Input type={'number'} value={post.price ? post.price : ''} placeholder={'금액'} min={0} onChange={handleChangePrice} />
                    {/* <label htmlFor='currency-field'>Enter Amount</label> */}
                    {/* <Input type='text' name='currency-field' id='currency-field' pattern='^\$\d{1,3}(,\d{3})*(\.\d+)?$' value='' data-type='currency' placeholder='$1,000,000.00' prefix={'₩'} /> */}
                </InputContainer>
                <InputContainer>
                    <Select value={post.transaction} onChange={handleChangeTransaction}> <Option value={''}> 거래방법 </Option> <Option value={'직거래'}>직거래</Option> <Option value={'택배거래'}>택배거래</Option></Select>
                </InputContainer>
                <InputContainer onClick={handleClickHashtag}>
                    <HashTag>
                        해시태그 <HashTagContainer>{postStore.hashtag.length} 개</HashTagContainer>
                    </HashTag>
                </InputContainer>
                <ContentsBox>
                    <ContentsText>상품설명</ContentsText>
                    <ContentsArea value={post.contents} onChange={handleChangeContents} />
                </ContentsBox>
            </FormContainer>
            <FooterContainer>
                <WrapperBtn onClick={handleSubmit} >
                    <CommonBtn type={'disable'} text={'등록하기'} />
                </WrapperBtn>
            </FooterContainer>
        </Wrapper>
    );
}

const Wrapper = styled.div`
    position: relative;
    width: 100%;
    height: 100%;
    padding: 1.6rem;
    grid-area: CC;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
`

const FormContainer = styled.div`
    width: 100%;
    height: 100%;
`

const ImageSelector = styled.div`
    display: flex;
    flex-direction: row;
    width: 100%;
    height: 4.5rem;
    margin: 0 0 1.6rem 0;
`

const FileInput = styled.input`
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip:rect(0,0,0,0);
    border: 0;
`

const ImageBtn = styled.label`
    display: flex;
    flex-direction: column;
    width: 4.5rem;
    height: 4.5rem;
    border: solid 1px #ddd;
    border-radius: 5px;
`

const ImageSpan = styled.span`
    font-size: 8px;
    color: #b3b3b3;
    margin: 0 auto auto auto;
`

const CameraIcon = styled.img`
    width: 34px;
    height: 34px;
    margin: auto auto 0 auto;
`

const PreviewContainer = styled.div`
    display: inline-block;
    width: 50vw;
    white-space: nowrap;
    overflow-x: scroll;
    ::-webkit-scrollbar {
        display: none;
    }
    margin: 0 2rem;
`

const PreviewImg = styled.img`
    width: 4.5rem;
    height: 4.5rem;
    margin: 0 0.5rem;
    border: solid 1px #ccc;
    border-radius: 5px;
`

const InputContainer = styled.div`
    display: flex;
    align-items: center;
    width: 100%;
    height: 3.5rem;
    border-bottom: solid 1px #ddd;
    margin: 0.5rem 0;
    vertical-align: center;
`

const Input = styled.input`
    width: 98%;
    height: 2rem;
    font-size: 15px;
    font-weight: bold;
    border: solid 0;
    color: #929292;
    padding: 0.5rem;
    ::placeholder {
        color:  #929292;
        font-weight: bold;
    }
    :focus {
        outline:  none !important;
    }
`

const Select = styled.select`
    -webkit-appearance: none;
       -moz-appearance: none;
            appearance: none;
    background: url(${DropdownIcon}) no-repeat 95% 50%;
    width: 100%;
    height: 2rem;
    font-size: 16px;
    color: #929292;
    font-weight: bold;
    border: solid 0;
    padding: 0.2rem;
    :focus {
        outline:  none !important;
    }
    ::selection {
        color:  pink;
    }
`

const Option = styled.option`
    color: #929292;
    font-weight: bold;
`

const HashTag = styled.div`
    background: url(${ExpandIcon}) no-repeat 95% 50%;
    width: 100%;
    height: auto;
    font-size: 15px;
    color: #929292;
    font-weight: bold;
    border: solid 0;
    padding: 0.5rem 0.2rem;
`

const HashTagContainer = styled.div`
    display: block;
    float: right;
    padding: 0 5rem;
`

const ContentsBox = styled.div`
    width: 100%;
    height: auto;
`

const ContentsText = styled.div`
    padding: 0.8rem 0;
    color: #666;
    font-weight: bold;
`

const ContentsArea = styled.textarea`
    width: 100%;
    height: 6rem;
    border: solid 1px #ddd;
    border-radius: 5px;
`

const FooterContainer = styled.div`
    display: flex;
    justify-content: flex-end;
    margin: 1.5rem 0 0 0;
`

const WrapperBtn = styled.div``
