import Link from 'next/link';
import * as React from 'react';
import styled from 'styled-components';
import HomeImage from '../../assets/img/home-none.png?url';
import useStores from '../../helpers/useStores';
interface INav {
    size?: {
        height: number,
    }
}

export default (props: INav) => {
    const {pageStore} = useStores();
    return(
        <WrapprNav size={props.size}>
            <WrapperNavItem>
                <img src={HomeImage} />
            </WrapperNavItem>
            <WrapperNavItem>
                1
            </WrapperNavItem>
            <WrapperNavItem>
                1
            </WrapperNavItem>
            <WrapperNavItem>
                1
            </WrapperNavItem>
            <WrapperNavItem>
                <Link href='/user'>
                    <a>User</a>
                </Link>
            </WrapperNavItem>
        </WrapprNav>
    )
}

const WrapprNav = styled.nav`
    position: fixed;
    display: flex;
    justify-content: space-around;
    bottom: 0;
    height: ${(props: Pick<INav, 'size'>) => props.size ? props.size.height : '48px'};
    width : 100%;
    background-color: lightblue;
    align-items: center;
`;

const WrapperNavItem = styled.div`
    background-color: yellow;
`;
