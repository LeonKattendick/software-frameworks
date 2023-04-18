import { Button, Layout, Space, Typography } from 'antd';
import { useRouter } from 'next/router';
import Background from '../assets/background.png';

const CustomLayout = ({ children }: React.PropsWithChildren) => {
  const router = useRouter();
  const isHome = router.pathname === '/';

  return (
    <Layout style={{ height: '100vh' }}>
      <Layout.Header style={{ backgroundColor: '#141414', zIndex: 1 }}>
        <div style={{ float: 'left' }}>
          <Typography.Text strong>MOBA Tracker</Typography.Text>
        </div>
        <Space style={{ float: 'right' }}>
          <Button onClick={() => router.push(isHome ? '/games' : '/')}>{isHome ? 'Spielvergleich' : 'Spielerliste'}</Button>
        </Space>
      </Layout.Header>
      <Layout.Content style={{ height: '100%', padding: '0 100px', color: 'white' }}>
        <img
          src={Background.src}
          alt=""
          style={{
            position: 'fixed',
            width: '100vw',
            height: '100vh',
            top: 0,
            left: 0,
            zIndex: 0,
            filter: 'blur(6px) grayscale(1)',
            transform: 'scale(1.1)',
            opacity: 0.1
          }}
        />
        <div style={{ zIndex: 1, height: '100%', display: 'flex', alignItems: 'center', justifyContent: 'center', flexDirection: 'column', gap: '3rem' }}>
          {children}
        </div>
      </Layout.Content>
    </Layout>
  );
};

export default CustomLayout;
