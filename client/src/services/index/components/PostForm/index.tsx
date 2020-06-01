import * as React from 'react';
import styled from '~/styled';

import { useRouter } from 'next/router';
import CommonBtn from '../../components/CommonBtn';

import { useCreatePostMutation } from '~/generated/graphql';
import CameraFillIcon from '../../assets/img/form-camera.png';
import DropdownIcon from '../../assets/img/form-dropdown.png';
import ExpandIcon from '../../assets/img/form-expand.png';
import useStores from '../../helpers/useStores';
import { IPostResponseDto } from '../../store/PostStore';

export interface IPostFormProps {
}

const category = ['디지털/가전', '가구/인테리어', '유아동/유아도서', '생활/가공식품', '스포츠/레저', '여성잡화', '여성의류', '남성패션/잡화', '게임/취미', '뷰티/미용', '반려동물용품', '도서/티켓/음반', '기타 중고물품'];

export default (props: IPostFormProps) => {
    const router = useRouter();
    const store = useStores();
    const postStore = store.postStore;
    const mutation = useCreatePostMutation();

    // File[] 안됌. Array()
    const [images, setImages] = React.useState(Array());
    const [previews, setPreviews] = React.useState(Array());
    const [post, setPost] = React.useState(postStore.getPost());
    const [value, setValue] = React.useState('');

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        e.preventDefault();
        if (e.target.files) {
            const files = Array.from(e.target.files);
            files.forEach((file) => {
                const reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onloadend = () => {
                    setPreviews([...previews, previews.concat(reader.result)]);
                }
            });
            previews.map((img) => {
                setImages([...images, images.concat(img.toString().slice(23))])
            })
        }
    };

    const handleChangeTitle = (event: any) => {
        postStore.setTitle(event.target.value);
    }
    const handleChangeCategory = (event: any) => {
        postStore.setCategory(event.target.value);
    }
    const handleChangePrice = (event: any) => {
        postStore.setPrice(event.target.value)
    }
    const handleChangeTransaction = (event: any) => {
        postStore.setTransaction(event.target.value);
    }
    const handleChangeContents = (event: any) => {
        postStore.setContents(event.target.value);
    }
    const handleSubmit = (event: any) => {
        event.preventDefault();
        console.log(images);
        const postData = {
            title: postStore.getTitle(),
            category: postStore.getCategory(),
            imgPaths: postStore.getImgPaths(),
            hashtag: postStore.getHashtag(),
            contents: postStore.getContents(),
            transaction: postStore.getTransaction(),
            price: postStore.getPrice(),
        }
        mutation({
            variables: {
                input: postData,
            },
        }).then((res: { data: IPostResponseDto }) => {
            console.log(res.data);
            console.log(postData);
            if (res.data.state === 'SUCCESS') {
                console.log(res.data.postId);
                router.push(`/post/${res.data.postId}`);
            }
        }).catch((error) => {
            console.log(error);
        })
    }

    return (
        <Wrapper>
            <FormContainer>
                <ImageSelector>
                    <ImageBtn htmlFor='img-file'>
                        <CameraIcon src={CameraFillIcon} />
                        <ImageSpan>{!images && '0/10'} {images && images.length + '/10'}</ImageSpan>
                    </ImageBtn>
                    <FileInput id={'img-file'} type={'file'} multiple accept={'image/png, image/jpeg, image/jpg'} capture={'camera'} onChange={handleFileChange} />
                    <PreviewContainer>
                        {
                            previews.map((preview, index) => (<PreviewImg src={preview} key={index} />))
                        }
                    </PreviewContainer>
                </ImageSelector>
                <InputContainer>
                    <Input type={'text'} placeholder={'제목'} onChange={handleChangeTitle} required />
                </InputContainer>
                <InputContainer>
                    <Select onChange={handleChangeCategory}> <Option value={''}> 카테고리 </Option>
                        {
                            category.map((category, index) => (
                                <Option key={index} value={category}>{category}</Option>
                            ))
                        }
                    </Select>
                </InputContainer>
                <InputContainer>
                    <Input type={'number'} placeholder={'금액'} min={0} onChange={handleChangePrice} required />
                </InputContainer>
                <InputContainer>
                    <Select onChange={handleChangeTransaction}> <Option value={''}> 거래방법 </Option> <Option value={'직거래'}>직거래</Option> <Option value={'택배거래'}>택배거래</Option></Select>
                </InputContainer>
                <InputContainer>
                    <HashTag>
                        해시태그 {postStore.hashtag}
                    </HashTag>
                </InputContainer>
                <ContentsBox>
                    <ContentsText>상품설명</ContentsText>
                    <ContentsArea onChange={handleChangeContents} />
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
    margin: 0 1rem;
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
    background: url(${DropdownIcon}) no-repeat 95% 50%;
    width: 100%;
    height: 2rem;
    font-size: 15px;
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
    height: 2rem;
    font-size: 15px;
    color: #929292;
    font-weight: bold;
    border: solid 0;
    padding: 0.5rem;
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
