import {getAllPlayers} from '@/util/services/playerApiService';
import {Button} from 'antd';
import {useEffect} from 'react';

const Home = () => {
  useEffect(() => {
    getAllPlayers().then(console.log);
  }, []);

  return (
    <>
      <Button>test</Button>
    </>
  );
};

export default Home;
